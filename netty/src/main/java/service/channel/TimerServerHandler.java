package service.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;

/**
 * Created by nick on 16/11/9.
 */
public class TimerServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String response = "HTTP/1.1 200 OK\n" +
                "Date: Sat, 31 Dec 2005 23:59:59 GMT\n" +
                "Content-Type: text/html;charset=ISO-8859-1\n" +
                "Content-Length: 122\n" +
                "\n" +
                "＜html＞\n" +
                "＜head＞\n" +
                "＜title＞Wrox Homepage＜/title＞\n" +
                "＜/head＞\n" +
                "＜body＞\n" +
                "＜!-- body goes here --＞\n" +
                "＜/body＞\n" +
                "＜/html＞";

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req);
        System.out.println(body);
        ByteBuf respBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(respBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server read complete!");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
