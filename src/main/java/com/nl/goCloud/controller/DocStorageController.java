package com.nl.goCloud.controller;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.UUID;

@RestController
public class DocStorageController {

    String connectionString="";

    String container="";

    BlobServiceClient blobService= new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

    @GetMapping("/readBlobFile")
    public String readBlobFile(String contentId) {
        return String.valueOf(
            blobService.getBlobContainerClient(container).getBlobClient(contentId+".content").downloadContent());

    }

    @PostMapping("/writeBlobFile")
    public String writeBlobFile(@RequestBody String data) {

        byte[] content= Base64.getDecoder().decode(data.getBytes());

        String documentId= UUID.randomUUID().toString();
        blobService.getBlobContainerClient(container).getBlobClient(documentId+".content").upload(BinaryData.fromBytes(content));
        return "file was updated";
    }
}
      
