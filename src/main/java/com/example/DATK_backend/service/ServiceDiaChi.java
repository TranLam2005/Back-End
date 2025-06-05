package com.example.DATK_backend.service;

import com.example.DATK_backend.entity.DiaChi;
import com.example.DATK_backend.repository.RepositoryDiaChi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDiaChi {
    private final RepositoryDiaChi repositoryDiaChi;
    public ServiceDiaChi(RepositoryDiaChi repositoryDiaChi) {
        this.repositoryDiaChi = repositoryDiaChi;
    }

    public DiaChi findDiaChiByUserId(int userId) {
        return repositoryDiaChi.findDiaChiByUserId(userId);
    }
    public void addDiaChi(int userId, String diaChi, String sdt) {
        repositoryDiaChi.addDiaChi(userId, diaChi, sdt);
    }
}
