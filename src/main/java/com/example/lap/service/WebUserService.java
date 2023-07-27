package com.example.lap.service;

import com.example.lap.dao.WebUser;
import com.example.lap.dto.RegisterWebUserDTO;
import com.example.lap.dto.WebUserDTO;

public interface WebUserService {
    WebUserDTO mapWebUserToWebUserDTO(WebUser user);

    WebUser mapRegisterWebUserDTOToWebUser(RegisterWebUserDTO user);
}
