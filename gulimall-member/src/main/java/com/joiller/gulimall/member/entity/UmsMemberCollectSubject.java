package com.joiller.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员收藏的专题活动
 * </p>
 *
 * @author jianghuilai
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsMemberCollectSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * subject_id
     */
    private Long subjectId;

    /**
     * subject_name
     */
    private String subjectName;

    /**
     * subject_img
     */
    private String subjectImg;

    /**
     * 活动url
     */
    private String subjectUrll;


}
