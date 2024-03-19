package com.stark.fisco.bcos.enums;

import lombok.Getter;

/**
 * 枚举账户文件格式
 *
 * @author <a href="mailto:mengbin@hotmail.com">Ben</a>
 * @version 1.0.0
 * @since 2024/3/18
 */
@Getter
public enum AccountFileFormat {

    pem(false),

    p12(true);

    private final boolean passwordRequired;

    AccountFileFormat(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

}
