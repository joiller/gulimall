package com.joiller.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UndoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long branchId;

    private String xid;

    private String context;

    private Blob rollbackInfo;

    private Integer logStatus;

    private LocalDateTime logCreated;

    private LocalDateTime logModified;

    private String ext;


}
