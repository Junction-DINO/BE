package dino.junction.domain.ocr.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class OcrService {

    public List<String> extractTextFromImageUrl(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        ByteString imgBytes;
        try (InputStream in = url.openStream()) {
            imgBytes = ByteString.readFrom(in);
        }

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();
        List<AnnotateImageRequest> requests = new ArrayList<>();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            StringBuilder sb = new StringBuilder();
            for (AnnotateImageResponse res : response.getResponsesList()) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    throw new Exception("Error: " + res.getError().getMessage());
                }
                sb.append(res.getFullTextAnnotation().getText());
            };
            return sb.toString().isEmpty() ?
                    new ArrayList<>() :
                    Stream.of(sb.toString())
                            .flatMap(str -> Stream.of(str.split("\n"))).toList();
        }
    }
}