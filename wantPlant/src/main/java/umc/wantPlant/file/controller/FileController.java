package umc.wantPlant.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.wantPlant.file.domain.S3FileDto;
import umc.wantPlant.file.service.AmazonS3Service;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final AmazonS3Service amazonS3Service;

    @Operation(summary = "파일 아마존S3에 업로드 후 업로드한 정보 반환",
            description = "입력값으론, 업로드할 파일의 디렉토리, 업로드할 파일 첨부. 반환되는 정보에는 원래 파일 이름, 파일의 URL, 파일을 업로드한 디렉토리, 파일을 업로드했을 때 이름")
    @PostMapping(value = "/uploads",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<S3FileDto> uploadFiles(
            @RequestParam(value = "uploadFilePath") String uploadFilePath,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {
        return amazonS3Service.uploadFiles(uploadFilePath, multipartFiles);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestParam(value = "uploadFilePath") String uploadFilePath,
            @RequestParam(value = "uuidFileName") String uuidFileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(amazonS3Service.deleteFile(uploadFilePath, uuidFileName));
    }
}
