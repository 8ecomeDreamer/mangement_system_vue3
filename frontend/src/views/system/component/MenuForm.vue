<template>
  <el-dialog
    :title="dialog.title"
    :visible.sync="dialog.addVisabled"
    width="928px"
    :append-to-body="true"
        custom-class="dialog-auto"
        top="40px"
  >
      <el-form :model="form" :rules="rules" ref="menuForm">
        <el-row class="inputClassDialog">
          <el-form-item label="菜单名称" class="inputClass-item" prop="name">
            <el-input placeholder="请输入菜单名称" v-model.trim="form.name" :disabled="onlyDisabled"></el-input>
          </el-form-item>
          <el-form-item label="路由地址" class="inputClass-item" prop="path">
            <el-input placeholder="请输入路由地址" v-model.trim="form.path" :disabled="onlyDisabled"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单" class="inputClass-item" prop="parentId">
            <el-select v-model="form.parentId" popper-class="option-table" placeholder="上级菜单" clearable @clear="form.parentId = ''"  :disabled="onlyDisabled">
              <el-option :label="form.parentName" :value="form.parentName" >
                <el-table :data="dialog.menuList" row-key="id" :show-header="false" :tree-props="{children: 'children', hasChildren: 'hasChildren'}" @row-click="selectMenu">
                  <el-table-column prop="name" label="名称"></el-table-column>
                </el-table>
              </el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row class="inputClassDialog">
          <el-form-item label="菜单排序" class="inputClass-item" prop="sortValue">
            <div
              style="display: inline-block;width: 100%;"
            >
              <el-input-number v-model="form.sortValue"  :min="1" :disabled="onlyDisabled" controls-position="right"></el-input-number>
            </div>
          </el-form-item>
          <el-form-item label="菜单图标" class="inputClass-item" prop="icon">
            <el-input placeholder="请点击右方按钮选择图标" v-model="form.icon" disabled>
              <template #append>
                <el-button @click="selectFlag = true"
                  icon="el-icon-folder-opened"
                  style="width: 40px; text-align:center;padding:0"
                ></el-button> </template
            ></el-input>
          </el-form-item>
          <el-form-item class="inputClass-item" label="组件地址" prop="component">
            <el-input placeholder="请输入组件地址" maxlength="64" v-model.trim="form.component" :disabled="onlyDisabled"></el-input>
          </el-form-item>
        </el-row>
        <el-row class="inputClassDialog">
          <el-form-item label="菜单类型" class="inputClass-item" prop="type" :disabled="onlyDisabled">
            <div
              style="display: inline-block;width: 100%;"
            >
              <el-radio v-model="form.type" :label="1" :disabled="onlyDisabled">菜单</el-radio>
              <el-radio v-model="form.type" :label="2" :disabled="onlyDisabled">按钮</el-radio>
            </div>
          </el-form-item>
          <el-form-item label="是否为外链（打开新窗口）" prop="isBlank" class="inputClass-item">
            <div
              style=" display:inline-block;width: 100%;"
            >
              <el-switch v-model="form.isBlank" :disabled="onlyDisabled" active-color="#409eff" inactive-color="#ff4949"> </el-switch>
            </div>
          </el-form-item>

        </el-row>
        <el-row class="inputClassDialog">
          <el-form-item class="inputClass-item" style="width:100%;margin:0 25px 32px 20px" label="备注">
            <el-input type="textarea" :rows="2" :disabled="onlyDisabled" placeholder="请输入备注" v-model.trim="form.description"> </el-input>
          </el-form-item>
        </el-row>

      </el-form>
      <!-- 确定取消按钮 -->
      <div class="footer_btn dialog_footer_btn" >
        <slot name="footer" class="dialog-footer">
          <el-button class="cancel" @click="cancel">取消</el-button>
          <el-button type="primary" class="sure" @click="submit">{{dialog.menuType === 'edit' ? '保 存' : '确 定' }}</el-button>
        </slot>
        <SelectIcon @selected="selected" @close="selectFlag = false" v-if="selectFlag" :selectFlag="selectFlag"></SelectIcon>
      </div>
  </el-dialog>
</template>

<script>
// import { getMenudetail, saveOrupdateMenu} from "@/api/servers/gdep_system";
import SelectIcon from './selectIcon'
import { selectMenu,insertMenu,updateMenu } from '@/api/system/menu'
export default {
  props: ["dialog"],
  components:
  {
    SelectIcon
  },
  data() {
    return {
      form: {
        type: 1,
        isBlank: false,
        name: '',
        parentId: '',
        parentName: '',
        path: '',
        description: '',
        sortValue: '',
        icon: '',
        noCache: 0,
        component: ''
      },
      selectFlag: false,
      onlyDisabled: false,
      rules: {
        name: [{ required: true, message: "请输入菜单名称", trigger: "blur" }],
        // parentId: [{ required: true, message: "请输入上级菜单", trigger: "blur" }],
        path: [{ required: true, message: "请输入路由地址", trigger: "blur" }],
        icon: [{ required: true, message: "请选择菜单图标", trigger: "blur" }],
        type: [{ required: true, message: "请选择菜单类型", trigger: "blur" }],
        isBlank: [{ required: true, message: "请选择是否为外链", trigger: "blur" }],
        sortValue: [{ required: true, message: "请选择排序", trigger: "blur" }],
      }
    };
  },
  mounted() {
    // console.log(this.dialog)
    // this.init();

    if(this.dialog.menuType == 'edit') {
      this.init();
    }
    // if(this.dialog.menuType == 'view') {
    // this.init();
    // this.onlyDisabled = true;
    // } else if(this.dialog.menuType == 'edit') {
    // this.init();
    // } else if(this.dialog.menuType == 'addmenu') {
    // this.form.parentId = this.dialog.menuName;
    // this.form.parentId = this.dialog.menuId;
    // }
  },
  methods: {
    cancel() {
    this.$parent.addDialogData.addVisabled = false;
    },
    selectMenu(value) {
    this.form.parentId = value.id;
    this.form.parentId = value.name
    },
     //选择图标
    selected(value) {
    this.form.icon = value;
    this.selectFlag = false;
    },
    init() {
      console.log('init')
      const obj = {
        page:1,
        limit:1,
        id: this.dialog.menuId,
        type:null,
        path: "",
        name: "",
        component: "",
        parentId: ""
      }
      selectMenu(obj).then(res => {
        if(res.code == '200') {
          console.log(res)
          this.form = res.data.list[0];
          this.form.isBlank = res.data.list.isBlank == '1' ? false : true
        } else {
          this.$message.error(res.msg);
        }
      })
    },
    submit() {
      this.$refs.menuForm.validate((valid) => {
        if(valid) {
          if (this.dialog.menuType === 'edit') {
            // 编辑
            this.updateMenu()
          } else if (this.dialog.menuType === 'add') {
            // 添加
            this.insertMenu()
          }
        }
      })
    },
    insertMenu(){
      // const obj = {}
      // console.log('ssss')
      this.form.isBlank = this.form.isBlank === true ? 1 : 0
      insertMenu(this.form).then(res => {
        if(res.code == '200') {
          this.$message.success(res.msg);
          this.$parent.addDialogData.addVisabled = false;
          this.$parent.fetchData();
        }
        else {
          this.$message.error(res.msg);
        }
      })
    },
    updateMenu(){
      this.form.id = this.dialog.menuId
      updateMenu(this.form).then(res => {
        if(res.code == '200') {
          this.$message.success(res.msg);
          this.$parent.addDialogData.addVisabled = false;
          this.$parent.fetchData();
        }
        else {
          this.$message.error(res.msg);
        }
      })
    }

    //        if(this.dialog.menuType === 'edit'){
    //          // 编辑
    //        }
    //        else if(this.dialog.menuType === 'add'){
    //          // 添加
    //        }
    //
    //        saveOrupdateMenu({
    //             ...this.form,
    //             'isBlank': this.form.isBlank == false ? '1' : '2',
    //           }).then(res => {
    //       if(res.code == '200') {
    //         this.$message.success(res.msg);
    //         this.$parent.addDialogData.addVisabled = false;
    //         this.$parent.getMenuList();
    //       }
    //       else {
    //         this.$message.error(res.msg);
    //       }
    //       })
    //      }
    //     });
    // }
  },
  watch: {}
};
</script>
<style lang="scss" scoped>
.dialog-auto {
  border-radius: 4px 4px 0px 0px;
  .inputClassDialog {
  width: 100%;
  display: flex;
  justify-content:space-between;


  ::v-deep .el-form-item__label {
    height: 32px;
    display: block;
    white-space: nowrap;
  }
  ::v-deep .inputClass-item {
    width: 272px;
    margin-bottom: 14px;
  }
}
.footer_btn {
  display: flex;
justify-content: flex-end;
padding-right: 20px;
border: none;
::v-deep .el-button {
margin-top: 0;
margin-bottom: 22px;
}
}
  ::v-deep .el-dialog__header{
  border-radius: 4px 4px 0px 0px;
  }
}
.option-table{
  .el-select-dropdown__wrap{
    margin-bottom: 0 !important;
  }
  .el-select-dropdown__list{
    padding: 0;
  }
  .el-select-dropdown__item{
    height: auto;
    padding: 0;
  }
  .el-table td{
    border: 0;
  }
}

::v-deep  .el-input--suffix .el-input__inner{
  padding-right: 100px !important;
}
</style>
