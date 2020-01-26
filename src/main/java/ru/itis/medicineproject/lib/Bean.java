package ru.itis.medicineproject.lib;

import ru.itis.medicineproject.dto.UserDto;

public class Bean {

    private static Bean bean;
    private UserDto userDto;

    public static Bean getInstance() {
        if (bean == null) {
            bean = new Bean();
        }
        return bean;
    }

    private Bean() {}

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void invalidate() {
        bean = null;
        userDto = null;
    }

}
