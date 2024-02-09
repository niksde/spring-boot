package com.niksde.graphqldemo.query;

import com.niksde.graphqldemo.service.StudentService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

@Component
public class Subscription implements GraphQLSubscriptionResolver {
    @Autowired
    StudentService studentService;

    public Publisher<String> test() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return Flux.interval(Duration.ofSeconds(3))
                .map(sequence -> "Value_" + sequence+ ": " + dateFormat.format(cal.getTime()));
    }
}
