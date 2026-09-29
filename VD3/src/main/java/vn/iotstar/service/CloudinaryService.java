package vn.iotstar.service;import org.springframework.web.multipart.MultipartFile;public interface CloudinaryService{CloudinaryUploadResult upload(MultipartFile f);void delete(String id);}
