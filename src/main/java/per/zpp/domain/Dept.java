package per.zpp.domain;

import java.util.Date;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:18
 * @Version 1.0 （版本号）
 */

public class Dept {
            private Integer id;
            private String deptName;
            private String address;
            private Date createDate;
            private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
