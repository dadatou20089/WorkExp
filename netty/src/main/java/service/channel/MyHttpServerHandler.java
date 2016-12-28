package service.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * Created by nick on 16/11/9.
 */
public class MyHttpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpRequest request = (HttpRequest) msg;

        System.out.println(request.method().name());
        System.out.println(request.uri());
        System.out.println(request.headers());
        System.out.println(request.protocolVersion());
        System.out.println(request.decoderResult());

        String content = "{" +
                "\"content\" : \"I'm OK\"," +
                "\"fileCount\": 100" +
                "}";

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes()));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json; charset=UTF-8");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaders.Names.CONNECTION, "keep-alive");

        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    public class content {
        private String content;
        private int count;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

    }
}
