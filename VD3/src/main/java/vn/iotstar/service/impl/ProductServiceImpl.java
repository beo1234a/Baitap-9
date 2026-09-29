package vn.iotstar.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.dto.ProductDTO;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.mapper.ProductMapper;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.service.CloudinaryService;
import vn.iotstar.service.CloudinaryUploadResult;
import vn.iotstar.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository pr;
    private final UserRepository ur;
    private final ProductMapper mapper;
    private final CloudinaryService cloud;

    public ProductServiceImpl(
            ProductRepository pr,
            UserRepository ur,
            ProductMapper mapper,
            CloudinaryService cloud) {

        this.pr = pr;
        this.ur = ur;
        this.mapper = mapper;
        this.cloud = cloud;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String k, int p, int s) {

        Pageable x = PageRequest.of(
                Math.max(0, p),
                Math.max(1, s),
                Sort.by(Sort.Direction.DESC, "id")
        );

        return pr.search(k == null ? "" : k, x)
                .map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        return mapper.toDTO(
                pr.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Product không tồn tại"))
        );
    }

    @Transactional
    public ProductDTO create(ProductDTO d, MultipartFile f) {

        User u = ur.findById(d.getUserId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User không tồn tại"));

        Product p = mapper.toEntity(d);

        p.setUser(u);

        if (d.getQuantity() == null) {
            p.setQuantity(0);
        } else {
            p.setQuantity(d.getQuantity());
        }

        if (f != null && !f.isEmpty()) {

            CloudinaryUploadResult r = cloud.upload(f);

            p.setImageUrl(
                    r.url() + "|" + r.publicId()
            );
        }

        return mapper.toDTO(pr.save(p));
    }

    @Transactional
    public ProductDTO update(
            Long id,
            ProductDTO d,
            MultipartFile f) {

        Product p = pr.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product không tồn tại"));

        p.setName(d.getName());
        p.setDescription(d.getDescription());
        p.setPrice(d.getPrice());

        if (d.getQuantity() == null) {
            p.setQuantity(0);
        } else {
            p.setQuantity(d.getQuantity());
        }

        if (f != null && !f.isEmpty()) {

            deleteImage(p.getImageUrl());

            CloudinaryUploadResult r = cloud.upload(f);

            p.setImageUrl(
                    r.url() + "|" + r.publicId()
            );
        }

        return mapper.toDTO(pr.save(p));
    }

    @Transactional
    public void delete(Long id) {

        Product p = pr.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product không tồn tại"));

        deleteImage(p.getImageUrl());

        pr.delete(p);
    }

    private void deleteImage(String x) {

        if (x != null && x.contains("|")) {

            cloud.delete(
                    x.substring(x.indexOf("|") + 1)
            );
        }
    }

    @Transactional(readOnly = true)
    public long countProducts() {
        return pr.count();
    }

    @Transactional(readOnly = true)
    public long countByUser(Long id) {
        return pr.countByUserId(id);
    }
}