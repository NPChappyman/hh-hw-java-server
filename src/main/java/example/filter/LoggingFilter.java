
package example.filter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import jakarta.ws.rs.core.MultivaluedMap;

@Provider
public class LoggingFilter implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ctx) throws java.io.IOException {


        byte[] buffer = ctx.getInputStream().readAllBytes();

        System.out.println("========== REQUEST ==========");
        System.out.println("На сервер поступил запрос");


        MultivaluedMap<String, String> headers = ctx.getHeaders();

        System.out.println("Заголовки:");

        if (headers != null && !headers.isEmpty()) {
            headers.forEach((key, values) -> {
                for (String value : values) {
                    System.out.println(key + ": " + value);
                }
            });
        } else {
            System.out.println("(нет заголовков)");
        }


        System.out.println("\nТело запроса:");

        String body = new String(buffer, StandardCharsets.UTF_8);

        if (body.isEmpty()) {
            System.out.println("(пустое тело)");
        } else {
            System.out.println(body);
        }

        System.out.println("============================\n");


        ctx.setInputStream(new ByteArrayInputStream(buffer));

        return ctx.proceed();
    }
}