package umc.wantPlant.file.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class S3File {

    private String originalFileName;

    private String uploadFileName;

    private String uploadFilePath;

    private String uploadFileUrl;
}
