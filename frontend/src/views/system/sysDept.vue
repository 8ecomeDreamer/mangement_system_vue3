/**
* 系统管理 部门管理
*/
<template>
  <div style="padding: 0 10px;">
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search"  style="display: flex;justify-content: center;align-items: center;">
      <el-form-item label="" >
        <el-input size="small" clearable v-model.trim="formInline.id" placeholder="输入id" ></el-input>
      </el-form-item>
      <el-form-item label="" >
        <el-input size="small" clearable v-model.trim="formInline.name" placeholder="输入部门名称" ></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.parentId" placeholder="输入上级部门id"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.leader" placeholder="输入负责人"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.phone" placeholder="输入电话"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table ref="basicTable" size="small" @selection-change="selectChange" :data="userData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column align="center" type="selection" width="50">
      </el-table-column>
      <el-table-column align="center"  prop="id" label="id" width="50" >
      </el-table-column>
      <el-table-column align="center"  prop="name" label="部门名称">
      </el-table-column>
      <el-table-column align="center"  prop="parentId" label="上级部门id">
      </el-table-column>
      <el-table-column align="center"  prop="treePath" label="树结构">
      </el-table-column>
      <el-table-column align="center"  prop="sortValue" label="排序">
      </el-table-column>
      <el-table-column align="center"  prop="leader" label="负责人">
      </el-table-column>
      <el-table-column align="center"  prop="treePath" label="树结构">
      </el-table-column>
      <el-table-column align="center"  prop="status" label="状态">
      </el-table-column>
      <el-table-column align="center"  prop="phone" label="电话">
      </el-table-column>
      <el-table-column align="center"  prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column align="center"  prop="updateTime" label="更新时间">
      </el-table-column>
      <el-table-column label="操作" align="center"  min-width="120" >
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.id, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteMenu(scope.id, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!--    <MenuForm :dialog="addDialogData" v-if="addDialogData.addVisabled" ></MenuForm>-->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click='closeDialog("edit")'>
      <el-form label-width="80px" ref="editForm" :model="editForm" :rules="rules">
        <el-form-item label="部门名称" prop="name">
          <el-input size="small" v-model="editForm.name" auto-complete="off" placeholder="请输入部门名称"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="roleCode">
          <el-input size="small" v-model="editForm.phone" auto-complete="off" placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input size="small" v-model="editForm.leader" auto-complete="off" placeholder="请输入负责人"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click='closeDialog("edit")'>取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
`
<script>
import Pagination from '@/components/common/Pagination.vue'
import MenuForm from './component/MenuForm.vue'
import { selectDept, deleteDept, insertDept,updateDept } from '@/api/system/dept'
export default {
  data() {
    return {
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      title: '添加用户',
      editFormVisible: false, //控制编辑页面显示与隐藏
      dataAccessshow: false, //控制数据权限显示与隐藏
      unitAccessshow: false, //控制所属单位隐藏与显示
      // 编辑与添加
      editForm: {
        // userId: '',
        userName: '',
        name: '',
        phone: '',
        leader: '',
        deptId: '',
        postId: ''
      },
      // 部门参数
      unitparm: {
        userIds: '',
        deptId: '',
        deptName: ''
      },
      // 选择数据
      selectdata: [],
      isInsertForm:true,
      // rules表单验证
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        roleCode: [
          { required: true, message: '请输入部门编码', trigger: 'blur' }
        ],
        // roleId: [{ required: true, message: '请选择部门', trigger: 'blur' }],
        // userMobile: [
        //   { required: true, message: '请输入手机号', trigger: 'blur' },
        //   {
        //     pattern: /^1(3\d|47|5((?!4)\d)|7(0|1|[6-8])|8\d)\d{8,8}$/,
        //     required: true,
        //     message: '请输入正确的手机号',
        //     trigger: 'blur'
        //   }
        // ],
        // userEmail: [
        //   { required: true, message: '请输入邮箱', trigger: 'blur' },
        //   {
        //     pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
        //     required: true,
        //     message: '请输入正确的邮箱',
        //     trigger: 'blur'
        //   }
        // ],
        // userSex: [{ required: true, message: '请选择性别', trigger: 'blur' }]
      },
      // 删除用户
      seletedata: {
        ids: '',
        token: localStorage.getItem('logintoken')
      },
      // 重置密码
      resetpsd: {
        userId: '',
        token: localStorage.getItem('logintoken')
      },
      // 用户下线
      offline: {
        token: localStorage.getItem('logintoken')
      },
      // 请求数据参数
      formInline: {
        id:null,
        page: 0,
        limit: 10,
        name: '',
        parentId: '',
        leader: '',
        phone: '',
      },
      //用户数据
      userData: [],
      // 数据权限
      UserDept: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      // 选中
      checkmenu: [],
      //参数role
      saveroleId: '',
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      addDialogData: {
        addVisabled: false,
        title: '新增',
        columnVisiable: false,
        menuList: [],
        menuType: 'add',
        menuId: '',
        menuName: ''
      },
    }
  },
  // 注册组件
  components: {
    Pagination,
    MenuForm
  },

  /**
   * 数据发生改变
   */
  watch: {
    isInsertForm(val){
      // console.log(val)
    }
  },

  /**
   * 创建完毕
   */
  created() {
    // this.getdata(this.formInline)
    this.fetchData()
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取数据方法
    fetchData(){
      this.loading = true
      console.log(this.formInline)
      selectDept(this.formInline).then(res => {
        console.log(res)
        this.userData = res.data.list
        this.pageparm.total = res.data.total
        // this.
      }).finally(()=>{
        this.loading = false
      })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.fetchData(this.formInline)
    },
    switchFormType(val){
      // 0:目录,1:菜单,2:按钮
      switch (val){
        case '目录':
          return 0
        case '菜单':
          return 1
        case '按钮':
          return 2
        default:
          return null
      }
    },
    //搜索事件
    search() {
      // 深拷贝 不影响原对象
      const params = JSON.parse(JSON.stringify(this.formInline))
      // params.type = this.switchFormType(this.formInline.type)
      this.formInline.type = this.switchFormType(this.formInline.type)
      // console.log(this.formInline)
      this.fetchData(this.formInline)
    },
    // 修改type
    editType: function(index, row) {
      this.loading = true
      let parm = {
        lock: '',
        userId: '',
        token: localStorage.getItem('logintoken')
      }
      parm.userId = row.userId
      // let lock = row.isLock
      if (lock == 'N') {
        parm.lock = 'Y'
      } else {
        parm.lock = 'N'
      }
      // 修改状态
      userLock(parm).then(res => {
        this.loading = false
        if (res.success == false) {
          this.$message({
            type: 'info',
            message: res.msg
          })
        } else {
          this.$message({
            type: 'success',
            message: '状态修改成功'
          })
          this.fetchData(this.formInline)
        }
      })
    },
    //显示编辑界面
    handleEdit: function(index, row) {
      this.editFormVisible = true
      // console.log(this.rules)
      if (row != undefined && row != 'undefined') {
        // 修改 只有修改界面才需要id
        console.log(row)
        // debugger
        this.editForm.id = row.id
        this.editForm.name = row.name
        this.editForm.roleCode = row.roleCode
        this.editForm.leader = row.leader
        this.isInsertForm = false
      } else {
        this.isInsertForm = true
      }
    },
    insertDeptInfo(){
      // 请求方法
      insertDept(this.editForm)
        .then(res => {
          console.log(res)
          this.editFormVisible = false
          this.loading = false
          if (res.code === '200') {
            this.fetchData(this.formInline)
            this.$message({
              type: 'success',
              message: '数据添加成功！'
            })
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.editFormVisible = false
          this.loading = false
          this.$message.error('保存失败，请稍后再试！')
        }).finally(()=>{
        this.isInsertForm = true
      })
    },
    updateDeptInfo(){
      updateDept(this.editForm)
        .then(res => {
          this.loading = false
          if (res.code === '200') {
            this.editFormVisible = false
            delete this.editForm.id
            this.fetchData(this.formInline)
            this.$message({
              type: 'success',
              message: '数据保存成功！'
            })
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.editFormVisible = false
          this.loading = false
          this.$message.error('保存失败，请稍后再试！')
        }).finally(()=>{
        // delete this.editForm.id
        this.isInsertForm = true
      })
    },
    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.isInsertForm) {
            console.log('insertDeptInfo')
            this.insertDeptInfo()
          } else {
            console.log('updateDeptInfo')
            this.updateDeptInfo()
          }
        } else {
          return false
        }
      })
    },
    // 显示部门设置
    handleunit: function(index, row) {
      this.unitAccessshow = true
      let parms = 0
      UserDeptdeptTree(parms)
        .then(res => {
          if (res.data.success) {
            this.UserDept = this.changeArr(res.data.data)
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('配置权限失败,请稍后再试！')
        })
    },
    handleClick(data, checked, node) {
      if (checked) {
        this.$refs.tree.setCheckedNodes([])
        this.$refs.tree.setCheckedNodes([data])
        this.unitparm.deptId = data.id
        this.unitparm.deptName = data.name
        //交叉点击节点
      } else {
      }
    },
    // 保存部门
    unitPermSave() {
      let len = this.selectdata
      let ids = []
      if (len != 0) {
        for (let i = 0; i < len.length; i++) {
          ids.push(len[i].userId)
        }
      }
      this.unitparm.userIds = ids.join(',')
      UserChangeDept(this.unitparm)
        .then(res => {
          this.unitAccessshow = false
          if (res.success) {
            this.$message({
              type: 'success',
              message: '部门设置成功！'
            })
            this.fetchData(this.formInline)
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('部门设置失败,请稍后再试！')
        })
    },
    // 选择复选框事件
    selectChange(val) {
      this.selectdata = val
    },
    // 关闭编辑、增加弹出框
    closeDialog(dialog) {
      if (dialog == 'edit') {
        this.editFormVisible = false
      } else if (dialog == 'perm') {
        this.dataAccessshow = false
      } else if (dialog == 'unit') {
        this.unitAccessshow = false
      }
    },
    // 删除用户
    deleteMenu(index, row) {
      const selectedData = this.$refs.basicTable.selection.map(el => el.id)
      if (selectedData.length <= 0){
        this.$message({
          type: 'error',
          message: '请选择需要操作的数据'
        })
        return
      }
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // console.log(selectedData)
        // debugger
        deleteDept(selectedData)
          .then(res => {
            // console.log(res)
            // debugger
            if (res.code === '200') {
              this.$message({
                type: 'success',
                message: '数据已删除!'
              })
              this.fetchData(this.formInline)
            } else {
              this.$message({
                type: 'info',
                message: res.msg
              })
            }
          })
          .catch(err => {
            console.log(err)
            this.loading = false
            this.$message.error('数据删除失败！')
          })
      })
        .catch(() => {
          this.$message({
            type: 'error',
            message: '数据删除失败！'
          })
        })
    },
    // 重置密码
    resetpwd(index, row) {
      this.resetpsd.userId = row.userId
      this.$confirm('确定要重置密码吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          userPwd(this.resetpsd)
            .then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: '重置密码成功！'
                })
                this.fetchData(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('重置密码失败，请稍后再试！')
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消重置密码！'
          })
        })
    },
    // 数据权限
    dataAccess: function(index, row) {
      this.dataAccessshow = true
      this.saveroleId = row.userId
      UserDeptTree(row.userId)
        .then(res => {
          if (res.data.success) {
            this.checkmenu = this.changemenu(res.data.data)
            this.UserDept = this.changeArr(res.data.data)
          } else {
            this.$message({
              type: 'info',
              message: res.data.msg
            })
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('获取权限失败，请稍后再试！')
        })
    },
    //数据格式化
    changeArr(data) {
      var pos = {}
      var tree = []
      var i = 0
      while (data.length != 0) {
        if (data[i].pId == 0) {
          tree.push({
            id: data[i].id,
            name: data[i].name,
            pId: data[i].pId,
            open: data[i].open,
            checked: data[i].checked,
            children: []
          })
          pos[data[i].id] = [tree.length - 1]
          data.splice(i, 1)
          i--
        } else {
          var posArr = pos[data[i].pId]
          if (posArr != undefined) {
            var obj = tree[posArr[0]]
            for (var j = 1; j < posArr.length; j++) {
              obj = obj.children[posArr[j]]
            }

            obj.children.push({
              id: data[i].id,
              name: data[i].name,
              pId: data[i].pId,
              open: data[i].open,
              checked: data[i].checked,
              children: []
            })
            pos[data[i].id] = posArr.concat([obj.children.length - 1])
            data.splice(i, 1)
            i--
          }
        }
        i++
        if (i > data.length - 1) {
          i = 0
        }
      }
      return tree
    },
    // 选中部门
    changemenu(arr) {
      let change = []
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked) {
          change.push(arr[i].id)
        }
      }
      return change
    },
    // 部门权限-保存
    menuPermSave() {
      let parm = {
        userId: this.saveroleId,
        deptIds: ''
      }
      let node = this.$refs.tree.getCheckedNodes()
      let moduleIds = []
      if (node.length != 0) {
        for (let i = 0; i < node.length; i++) {
          moduleIds.push(node[i].id)
        }
        parm.deptIds = JSON.stringify(moduleIds)
      }
      UserDeptSave(parm)
        .then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '权限保存成功'
            })
            this.dataAccessshow = false
            this.fetchData(this.formInline)
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('权限保存失败，请稍后再试！')
        })
    },
    // 下线用户
    offlineUser(index, row) {
      this.$confirm('确定要让' + row.userName + '用户下线吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          userExpireToken(row.userName)
            .then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: '用户' + row.userName + '强制下线成功！'
                })
                this.fetchData(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('用户下线失败，请稍后再试！')
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    },
    // 刷新缓存
    refreshCache(index, row) {
      userFlashCache(row.userName)
        .then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '刷新成功！'
            })
            this.fetchData(this.formInline)
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('刷新失败，请稍后再试！')
        })
    }
  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userDept {
  width: 100%;
}
</style>
