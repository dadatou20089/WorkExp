package container.protocals;

import container.Container;
import container.containers.entities.ContainerModule;
import container.containers.entities.ContainerObject;
import container.protocals.ncp.NcpProtocolInterceptor;
import container.single.SingleContainer;
import http.NcpProtocal;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;
import utils.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nick on 16/12/30.
 */
public abstract class ProtocolInterceptor implements MethodInterceptor {
    private static Logger logger = MyLogger.getLogger(NcpProtocolInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        NcpProtocal ncpProtocal = new NcpProtocal();
        Map<String, Object> map = new LinkedHashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i ++) {
            map.put(parameters[i].getName(), objects[i]);
        }

        String className = o.getClass().getName().split("\\$\\$")[0];
        String methodName = method.getName();
//        String params = ncpProtocal.getNcpRequestBody(map, className, methodName);
        String params = getParameters(map, className, methodName);

        logger.info(params);
        logger.info("className:" + className + ", methodName: " + methodName);
        logger.info("args:" + map);

        Container container = SingleContainer.getInstance();
        ContainerModule module = container.getModule();
        ContainerObject object = module.getContainerObjectByClassName(className);
        String uri = object.getServerUri(); //"http://192.168.113.225:8488/rpc"

        logger.info(uri);

        String result = sendPost(uri, params);
        logger.info(result);
        return result;
    }

    /***
     * 获取序列化请求参数
     */
    public abstract String getParameters(Map<String, Object> params, String className, String methodName);

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("发送 POST 请求出现异常！"+e);
            logger.error(e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
