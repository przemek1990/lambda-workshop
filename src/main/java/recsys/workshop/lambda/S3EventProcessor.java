package recsys.workshop.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class S3EventProcessor implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event s3event, Context context) {
        S3EventNotificationRecord record = s3event.getRecords().get(0);

        LambdaLogger logger = context.getLogger();

        String srcBucket = record.getS3().getBucket().getName();
        // Object key may have spaces or unicode non-ASCII characters.
        String srcPath = record.getS3().getObject().getKey().replace('+', ' ');
        try {
            srcPath = URLDecoder.decode(srcPath, "UTF-8");

            logger.log("Bucket: " + srcBucket);
            logger.log("Path: " + srcPath);

            //PUT YOUR CODE HERE
            //USEFUL LINK -> http://docs.aws.amazon.com/lambda/latest/dg/java-wt-s3-log-event-data.html

            return null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
