<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input placeholder="用户名" v-model="searchModel.username" clearable></el-input>
          <el-input placeholder="电话" v-model="searchModel.phone" clearable></el-input>
          <el-button @click="getUserList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" @click="addUser" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="userList" stripe style="width: 100%">
        <el-table-column type="index" label="#" width="180">
        </el-table-column>
        <el-table-column prop="id" label="用户ID" width="180">
        </el-table-column>
        <el-table-column prop="username" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="phone" label="电话" width="180">
        </el-table-column>
        <el-table-column prop="status" label="用户状态" width="180">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == 1">正常</el-tag>
            <el-tag v-else type="danger">禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="editUser(scope.row)" size="mini" circle></el-button>
            <el-button type="danger" icon="el-icon-delete" @click="deleteUser(scope.row)" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[5, 10, 20, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
    <el-dialog :title="title" @close="clearForm" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" ref="userFormRef" :rules="rules" label-width="auto" label-position="right">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="userForm.id == null || userForm.id == undefined" label="密码" prop="password">
          <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="userForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户状态" prop="status">
          <el-switch v-model="userForm.status" :active-value="1" :inactive-value="0">
          </el-switch>
        </el-form-item>
        <el-form-item label="邮件" prop="email">
          <el-input v-model="userForm.email" autocomplete="off"></el-input>
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
import { getUserList, addUser, updataUser, deleteById } from '@/api/user'
import { resetRouter } from '@/router';

export default {
  data() {
    var checkEmail = (rule, value, callback) => {
      var reg = /\w[-.\w]*\@[-a-z0-9]+(\.[-a-z0-9]+)*\.(com|cn|edu|uk)/ig
      if (!reg.test(value)) {
        return callback(new Error('邮箱格式错误'));
      }
      callback();
    };
    return {
      total: 0,
      searchModel: {
        username: null,
        phone: null,
        pageNo: 1,
        pageSize: 10,
      },
      userList: [],
      title: "",
      dialogFormVisible: false,
      userForm: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入初始密码', trigger: 'blur' },
          { min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮件', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
      },
    };
  },
  methods: {
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
    getUserList() {
      getUserList(this.searchModel)
        .then(response => {
          // console.log(response)
          var data = response.data;
          this.userList = data.content;
          this.total = data.totalElements;
          // console.log(this.userList);
          // console.log(this.total);
        });
    },
    addUser() {
      this.title = '新增用户';
      this.dialogFormVisible = true;
    },
    editUser(row) {
      this.userForm = row;
      this.title = '修改用户';
      this.dialogFormVisible = true;
    },
    clearForm() {
      this.userForm = {};
      this.$refs.userFormRef.clearValidate();
    },
    submitForm() {
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          if (this.title == '新增用户') {
            addUser(this.userForm)
              .then(response => {
                this.$message({
                  message: "新增用户成功",
                  type: 'success'
                });
                this.dialogFormVisible = false;
                this.getUserList();
              })
              .catch({
                message: "新增用户失败",
                type: 'error'
              });
          } else if (this.title == '修改用户') {
            updataUser(this.userForm)
              .then(response => {
                this.$message({
                  message: "修改用户成功",
                  type: 'success'
                });
                this.dialogFormVisible = false;
                this.getUserList();
              })
              .catch({
                message: "修改用户失败",
                type: 'error'
              });
          } else {
            console.log('error submit!!');
            return false;
          }
        }
      });
    },
    deleteUser(row) {
      this.$confirm('确认删除用户 ${row.username} ?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warninng'
      }).then(() => {
        deleteById(id)
          .then(response => {
            this.$message({
              message: "删除用户成功",
              type: 'success'
            });
            this.getUserList();
          })
          .catch({
            message: "删除用户失败",
            type: 'error'
          });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      });
    },
  },
  created() {
    this.getUserList();
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