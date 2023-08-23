import request from '@/utils/request'

export function getInfo(token) {
  return request({
    url: '/admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function getUserList(searchModel) {
  return request({
    url: '/admin/user/list',
    method: 'get',
    params: {
      username: searchModel.username,
      phone: searchModel.phone,
      pageNo: searchModel.pageNo,
      pageSize: searchModel.pageSize,
    }
  })
}

export function addUser(user) {
  return request({
    url: '/admin/user/add',
    method: 'post',
    contentType: "application/json",
    data: user,
  })
}

export function updataUser(user) {
  return request({
    url: '/admin/user/edit',
    method: 'put',
    contentType: "application/json",
    data: user,
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/user/deleteById/' + id,
    method: 'delete',
  })
}