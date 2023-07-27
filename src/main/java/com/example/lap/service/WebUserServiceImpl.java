package com.example.lap.service;

import com.example.lap.dao.WebUser;
import com.example.lap.dto.RegisterWebUserDTO;
import com.example.lap.dto.WebUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebUserServiceImpl implements WebUserService{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WebUserDTO mapWebUserToWebUserDTO(WebUser user) {
        WebUserDTO webUserDTO = modelMapper.map(user, WebUserDTO.class);
        return webUserDTO;
    }

    @Override
    public WebUser mapRegisterWebUserDTOToWebUser(RegisterWebUserDTO user) {
        WebUser webUserDTO = modelMapper.map(user, WebUser.class);
        return webUserDTO;
    }
}
