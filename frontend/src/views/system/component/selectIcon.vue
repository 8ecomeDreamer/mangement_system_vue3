<template>
   <el-dialog
      title="选择图标"
      :visible="selectFlag"
      width="30%"  :append-to-body="true"
      :close-on-click-modal="false" @close="close">
     <div>
      <SelectIcon @selected="select"></SelectIcon>
     </div>
    </el-dialog>
</template>

<script>
import SelectIcon from '@/components/common/IconSelect/index'
export default {
  props: ["selectFlag"],
  components: {
    SelectIcon,
  },
  data() {
    return {
       ruleForm: {
        roleName: '',
        roleAlias: '',
        sort: '',
        tenantId: '000000'
      },
      onlyDisabled: false,
      rules: {
        roleName: [
          {
            required: true, message: '请输入角色名称', trigger: 'blur'
          }
        ],
        roleAlias: [
          {
            required: true, message: '请输入角色别名', trigger: 'blur'
          }
        ],
        sort: [
          {
            required: true, message: '请输入排列顺序', trigger: 'blur'
          }
        ]
      },
    };
  },
  mounted() {
  },
  methods: {
    select(value) {
    this.$emit('selected', value)
    },
    close() {
      this.$emit('close');
    },
  },
  watch: {}
};
</script>
<style lang="scss" scoped>
 .coll-item{
    width: 75%;
    margin: 10px auto;
  }
  .dialog-footer{
    display: flex;
   justify-content: flex-end;
    margin-top: 26px;
    padding-right: 60px;
    margin-bottom: 16px;
  }
</style>
