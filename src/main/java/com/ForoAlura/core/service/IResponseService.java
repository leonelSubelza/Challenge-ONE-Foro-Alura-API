package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.response.ResponseRegister;
import com.ForoAlura.core.dto.response.ResponseReturnDTO;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;

public interface IResponseService {
    public Page<ResponseReturnDTO> readAll(Pageable pageable);
    public ResponseReturnDTO findById(Long id);
    public ResponseReturnDTO update(Long id, ResponseRegister responseRegister);
    public ResponseReturnDTO create(ResponseRegister responseRegister);
    public void delete(Long id);
}
