package com.ForoAlura.core.service;

import com.ForoAlura.core.dto.response.ResponseRegister;
import com.ForoAlura.core.dto.response.ResponseReturnDetailedDTO;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;

public interface IResponseService {
    public Page<ResponseReturnDetailedDTO> readAll(Pageable pageable);
    public ResponseReturnDetailedDTO findById(Long id);
    public ResponseReturnDetailedDTO update(Long id, ResponseRegister responseRegister);
    public ResponseReturnDetailedDTO create(ResponseRegister responseRegister);
    public void delete(Long id);
}
