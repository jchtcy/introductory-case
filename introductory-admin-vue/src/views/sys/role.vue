<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input placeholder="角色名称" v-model="searchModel.roleName" clearable></el-input>
          <el-button @click="getRoleList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" @click="addRole" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="roleList" stripe style="width: 100%">
        <el-table-column prop="roleId" label="角色ID" width="200">
        </el-table-column>
        <el-table-column prop="roleName" label="角色名称" width="260">
        </el-table-column>
        <el-table-column prop="roleDesc" label="角色描述">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="editRole(scope.row)" size="mini" circle></el-button>
            <el-button type="danger" icon="el-icon-delete" @click="deleteRole(scope.row)" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[5, 10, 20, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
    <el-dialog :title="title" @close="clearForm" :visible.sync="dialogFormVisible">
      <el-form :model="roleForm" ref="roleFormRef" :rules="rules" label-width="auto" label-position="right">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="roleForm.roleDesc" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限设置" prop="menuIdList">
          <el-tree ref="menuRef" :data="menuList" node-key="menuId" show-checkbox :props="menuProps" default-expand-all >
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRoleList, addRole, updataRole, deleteById } from '@/api/role'
import { getMenuList,  } from '@/api/menu'
export default {
  data() {
    return {
      menuList: [],
      menuProps: {
        children: 'children',
        label: 'title',
      },
      total: 0,
      searchModel: {
        roleName: null,
        pageNo: 1,
        pageSize: 10,
      },
      roleList: [],
      title: "",
      dialogFormVisible: false,
      roleForm: {},
      rules: {
        roleName: [
          { required: true, message: '请输入角色名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        roleDesc: [
          { required: true, message: '请输入角色描述', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
      },
    };
  },
  methods: {
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
    getRoleList() {
      getRoleList(this.searchModel)
        .then(response => {
          // console.log(response)
          var data = response.data;
          this.roleList = data.content;
          this.total = data.totalElements;
          // console.log(this.userList);
          // console.log(this.total);
        });
    },
    addRole() {
      this.title = '新增角色';
      this.dialogFormVisible = true;
    },
    editRole(row) {
      this.roleForm = row;
      this.title = '修改角色';
      this.$nextTick(() => {
        this.$refs.menuRef.setCheckedKeys(row.menuIdList);
      })
      this.dialogFormVisible = true;
    },
    clearForm() {
      this.roleForm = {};
      this.$refs.roleFormRef.clearValidate();
      this.$refs.menuRef.setCheckedNodes([]);
    },
    submitForm() {
      this.$refs.roleFormRef.validate((valid) => {
        if (valid) {
          let CheckedKeys = this.$refs.menuRef.getCheckedKeys();
          let halfCheckedKeys = this.$refs.menuRef.getHalfCheckedKeys();
          // console.log(CheckedKeys);
          // console.log(halfCheckedKeys);
          this.roleForm.menuIdList = CheckedKeys.concat(halfCheckedKeys);
          // console.log(this.roleForm)
          if (this.title == '新增角色') {
            addRole(this.roleForm)
              .then(response => {
                this.$message({
                  message: "新增角色成功",
                  type: 'success'
                });
                this.dialogFormVisible = false;
                this.getRoleList();
              })
              .catch({
                message: "新增角色失败",
                type: 'error'
              });
          } else if (this.title == '修改角色') {
            updataRole(this.roleForm)
              .then(response => {
                this.$message({
                  message: "修改角色成功",
                  type: 'success'
                });
                this.dialogFormVisible = false;
                this.getRoleList();
              })
              .catch({
                message: "修改角色失败",
                type: 'error'
              });
          } else {
            console.log('error submit!!');
            return false;
          }
        }
      });
    },
    deleteRole(row) {
      this.$confirm(`确认删除角色 ${row.roleName} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warninng'
      }).then(() => {
        deleteById(row.roleId)
          .then(response => {
            this.$message({
              message: "删除角色成功",
              type: 'success'
            });
            this.getRoleList();
          })
          .catch({
            message: "删除角色失败",
            type: 'error'
          });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      });

    },
    getMenuList(){
      getMenuList().then(response => {
        this.menuList = response.data;
      })
    },
  },
  created() {
    this.getRoleList();
    this.getMenuList();
  }
}
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}

.el-dialog .el-input {
  width: 50%;
}
</style>