package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志记录
 * @TableName sys_oper_log
 */
@TableName(value ="sys_oper_log")
@Data
public class SysOperLog implements Serializable {
    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "business_type")
    private String business_type;

    /**
     * 方法名称
     */
    @TableField(value = "method")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private String request_method;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    private String operator_type;

    /**
     * 操作人员
     */
    @TableField(value = "oper_name")
    private String oper_name;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String dept_name;

    /**
     * 请求URL
     */
    @TableField(value = "oper_url")
    private String oper_url;

    /**
     * 主机地址
     */
    @TableField(value = "oper_ip")
    private String oper_ip;

    /**
     * 请求参数
     */
    @TableField(value = "oper_param")
    private String oper_param;

    /**
     * 返回参数
     */
    @TableField(value = "json_result")
    private String json_result;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    private String error_msg;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    private Date oper_time;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableField(value = "is_deleted")
    private Integer is_deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysOperLog other = (SysOperLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getBusiness_type() == null ? other.getBusiness_type() == null : this.getBusiness_type().equals(other.getBusiness_type()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getRequest_method() == null ? other.getRequest_method() == null : this.getRequest_method().equals(other.getRequest_method()))
            && (this.getOperator_type() == null ? other.getOperator_type() == null : this.getOperator_type().equals(other.getOperator_type()))
            && (this.getOper_name() == null ? other.getOper_name() == null : this.getOper_name().equals(other.getOper_name()))
            && (this.getDept_name() == null ? other.getDept_name() == null : this.getDept_name().equals(other.getDept_name()))
            && (this.getOper_url() == null ? other.getOper_url() == null : this.getOper_url().equals(other.getOper_url()))
            && (this.getOper_ip() == null ? other.getOper_ip() == null : this.getOper_ip().equals(other.getOper_ip()))
            && (this.getOper_param() == null ? other.getOper_param() == null : this.getOper_param().equals(other.getOper_param()))
            && (this.getJson_result() == null ? other.getJson_result() == null : this.getJson_result().equals(other.getJson_result()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getError_msg() == null ? other.getError_msg() == null : this.getError_msg().equals(other.getError_msg()))
            && (this.getOper_time() == null ? other.getOper_time() == null : this.getOper_time().equals(other.getOper_time()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getIs_deleted() == null ? other.getIs_deleted() == null : this.getIs_deleted().equals(other.getIs_deleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getBusiness_type() == null) ? 0 : getBusiness_type().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getRequest_method() == null) ? 0 : getRequest_method().hashCode());
        result = prime * result + ((getOperator_type() == null) ? 0 : getOperator_type().hashCode());
        result = prime * result + ((getOper_name() == null) ? 0 : getOper_name().hashCode());
        result = prime * result + ((getDept_name() == null) ? 0 : getDept_name().hashCode());
        result = prime * result + ((getOper_url() == null) ? 0 : getOper_url().hashCode());
        result = prime * result + ((getOper_ip() == null) ? 0 : getOper_ip().hashCode());
        result = prime * result + ((getOper_param() == null) ? 0 : getOper_param().hashCode());
        result = prime * result + ((getJson_result() == null) ? 0 : getJson_result().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getError_msg() == null) ? 0 : getError_msg().hashCode());
        result = prime * result + ((getOper_time() == null) ? 0 : getOper_time().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getIs_deleted() == null) ? 0 : getIs_deleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", business_type=").append(business_type);
        sb.append(", method=").append(method);
        sb.append(", request_method=").append(request_method);
        sb.append(", operator_type=").append(operator_type);
        sb.append(", oper_name=").append(oper_name);
        sb.append(", dept_name=").append(dept_name);
        sb.append(", oper_url=").append(oper_url);
        sb.append(", oper_ip=").append(oper_ip);
        sb.append(", oper_param=").append(oper_param);
        sb.append(", json_result=").append(json_result);
        sb.append(", status=").append(status);
        sb.append(", error_msg=").append(error_msg);
        sb.append(", oper_time=").append(oper_time);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}