/**
* 系统管理 菜单管理
*/
<template>
  <div style="padding: 0 10px;">
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search"  style="display: flex;justify-content: center;align-items: center;">
      <!--      <el-form-item label="搜索：">-->
      <!--        <el-select size="small" v-model="formInline.isNoDeleted" placeholder="请选择">-->
      <!--          <el-option label="全部" value=""></el-option>-->
      <!--          <el-option label="" value=""></el-option>-->
      <!--          <el-option label="" value=""></el-option>-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item label="" >
        <el-input size="small" clearable v-model.trim="formInline.parentId" placeholder="输入上级路由" ></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.name" placeholder="输入路由名称"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.type" placeholder="输入路由类型"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.path" placeholder="输入路由地址"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" clearable v-model.trim="formInline.component" placeholder="输入组件路径"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
        <!--        <el-button size="small" type="primary" @click="handleunit()">部门设置</el-button>-->
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table ref="basicTable" size="small" @selection-change="selectChange" :data="userData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column align="center" type="selection" width="50">
      </el-table-column>
      <el-table-column align="center"  prop="id" label="id" width="50" >
      </el-table-column>
      <el-table-column align="center"  prop="parentId" label="所属上级">
      </el-table-column>
      <el-table-column align="center"  prop="name" label="名称">
      </el-table-column>
      <el-table-column align="center"  prop="sortValue" label="顺序">
      </el-table-column>
      <el-table-column align="center"  prop="type" label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type === 0 ? '目录' :  (scope.row.type === 1 ? '菜单' : '按钮') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="path" label="路由地址" >
      </el-table-column>
      <el-table-column
        prop="action"
        label="菜单图标"
        align="center"
      >
        <template v-slot="scoped">
          {{scoped.row.icon}}
<!--          <svg-icon :icon-class="scoped.row.icon || ''"/>-->
        </template>
      </el-table-column>
      <el-table-column align="center" sortable prop="component" label="组件路径" >
      </el-table-column>
      <el-table-column align="center"  prop="perms" label="权限标识">
      </el-table-column>
      <el-table-column align="center" sortable prop="createTime" label="创建时间" width="140">
      </el-table-column>
      <el-table-column align="center" sortable prop="updateTime" label="更新时间" width="140">
      </el-table-column>
      <!--      <el-table-column align="center"  prop="status" label="状态" width="50">-->
      <!--      </el-table-column>-->
      <el-table-column label="操作" align="center"  min-width="120" >
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.id, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteMenu(scope.id, scope.row)">删除</el-button>
          <!--          <el-button size="mini" type="success" @click="resetpwd(scope.$index, scope.row)">重置密码</el-button>-->
          <!--          <el-button size="mini" type="success" @click="dataAccess(scope.$index, scope.row)">数据权限</el-button>-->
          <!--          <el-button size="mini" type="success" @click="offlineUser(scope.$index, scope.row)">下线</el-button>-->
          <!--          <el-button size="mini" type="success" @click="refreshCache(scope.$index, scope.row)">刷新缓存</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!-- 编辑界面 -->
<!--    <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click='closeDialog("edit")'>-->
<!--      <el-form label-width="80px" ref="editForm" :model="editForm" :rules="rules">-->
<!--        <el-form-item label="用户名" prop="userName">-->
<!--          <el-input size="small" v-model="editForm.userName" auto-complete="off" placeholder="请输入用户名"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="姓名" prop="userName">-->
<!--          <el-input size="small" v-model="editForm.name" auto-complete="off" placeholder="请输入真实姓名"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="手机号">-->
<!--          <el-input size="small" v-model="editForm.phone" auto-complete="off" placeholder="请输入手机号"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="岗位" >-->
<!--          &lt;!&ndash;          <el-input size="small" v-model="editForm.deptId" auto-complete="off" placeholder="请输入岗位id"></el-input>&ndash;&gt;-->
<!--          <el-select size="small" v-model="editForm.deptId" placeholder="请输入岗位id">-->
<!--            <el-option label="1021" value="1021"></el-option>-->
<!--            <el-option label="1022" value="1022"></el-option>-->
<!--            <el-option label="1024" value="1024"></el-option>-->
<!--            <el-option label="1025" value="1025"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="部门" >-->
<!--          &lt;!&ndash;          <el-input size="small" v-model="editForm.postId" auto-complete="off" placeholder="请输入部门id"></el-input>&ndash;&gt;-->
<!--          <el-select size="small" v-model="editForm.postId" placeholder="请输入部门id">-->
<!--            <el-option label="5" value="5"></el-option>-->
<!--            <el-option label="6" value="6"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注" >-->
<!--          <el-input size="small" v-model="editForm.description" auto-complete="off" placeholder="请输入用户名"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button size="small" @click='closeDialog("edit")'>取消</el-button>-->
<!--        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
<!--    弹窗-->
    <MenuForm :dialog="addDialogData" v-if="addDialogData.addVisabled" ></MenuForm>

  </div>
</template>

<script>
// 导入请求方法
// import {
//   userList,
//   userSave,
//   userDelete,
//   userPwd,
//   userExpireToken,
//   userFlashCache,
//   userLock,
//   UserDeptTree,
//   UserDeptSave,
//   UserDeptdeptTree,
//   UserChangeDept
// } from '../../api/userMG'
import Pagination from '@/components/common/Pagination.vue'
import MenuForm from './component/MenuForm.vue'
import { deleteUserInfo, insertUserInfo, selectUserInfo, updateUserInfo } from '@/api/system/user'
import { selectMenu,deleteMenu } from '@/api/system/menu'
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
        description: '',
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
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        // roleId: [{ required: true, message: '请选择角色', trigger: 'blur' }],
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
        page: 0,
        limit: 10,
        type: '',
        path: '',
        name: '',
        component: '',
        parentId: ''
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
      console.log(val)
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
      selectMenu(this.formInline).then(res => {
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
      this.addDialogData.addVisabled = true;
      // console.log(this.rules)
      if (row != undefined && row != 'undefined') {
        // 修改 只有修改界面才需要id
        this.addDialogData.title = '编辑'
        this.addDialogData.menuId = row.id
        this.addDialogData.menuType = 'edit'
      } else {
        this.addDialogData.title = '新增'
        this.addDialogData.menuType = 'add'
      }
    },
    insertUserInfo(){
      // 请求方法
      insertUserInfo(this.editForm)
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
    updateUserInfo(){
      updateUserInfo(this.editForm)
        .then(res => {
          this.editFormVisible = false
          this.loading = false
          if (res.code === '200') {
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
        delete this.editForm.id
        this.isInsertForm = true
      })
    },
    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.isInsertForm) {
            this.insertUserInfo()
            // console.log('insertUserInfo')
            // debugger
          } else {
            this.updateUserInfo()
            // console.log('updateUserInfo')
            // debugger
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
          debugger
          deleteMenu(selectedData)
            .then(res => {
              console.log(res)
              debugger
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
    // 选中菜单
    changemenu(arr) {
      let change = []
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked) {
          change.push(arr[i].id)
        }
      }
      return change
    },
    // 菜单权限-保存
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
.userRole {
  width: 100%;
}
</style>
