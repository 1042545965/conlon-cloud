//package conlon.cloud.canal.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import lombok.Data;
//import lombok.experimental.Accessors;
//
///**
// * <p>
// * 用户信息表bs_user
// * </p>
// *
// * @author conlon
// * @since 2020-05-29
// */
//@Data
//@Accessors(chain = true)
//@Table(name = "bs_user_info")
//public class UserInfoEntity implements Serializable {
//
//
//    private static final long serialVersionUID = -2165245394096535954L;
//    /**
//     * id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    /**
//     * 用户名称
//     */
//    @Column(name = "user_name")
//    private String userName;
//
//
//    /**
//     * 用户编码
//     */
//    @Column(name = "user_code")
//    private String userCode;
//
//
//    /**
//     * 用户昵称
//     */
//    @Column(name = "nick_name")
//    private String nickName;
//
//
//    /**
//     * 用户密码
//     */
//    @Column(name = "password")
//    private String password;
//
//
//    /**
//     * 创建时间
//     */
//    @Column(name = "created_time")
//    private Date createdTime;
//
//
//    /**
//     * 创建人ID
//     */
//    @Column(name = "created_user_id")
//    private Long createdUserId;
//
//
//    /**
//     * 创建人姓名
//     */
//    @Column(name = "created_user_name")
//    private String createdUserName;
//
//
//    /**
//     * 最后修改时间
//     */
//    @Column(name = "modified_time")
//    private Date modifiedTime;
//
//
//    /**
//     * 最后修改人ID
//     */
//    @Column(name = "modified_user_id")
//    private Long modifiedUserId;
//
//
//    /**
//     * 最后一次修改用户名称
//     */
//    @Column(name = "modified_user_name")
//    private String modifiedUserName;
//
//
//    /**
//     * 删除状态1表示删除，0表示未删除
//     */
//    @Column(name = "is_deleted")
//    private Integer isDeleted;
//
//
//    /**
//     * 状态（0正常，1停用）
//     */
//    @Column(name = "status")
//    private Integer status;
//
//
//    /**
//     * 备注
//     */
//    @Column(name = "remarks")
//    private String remarks;
//
//
//}