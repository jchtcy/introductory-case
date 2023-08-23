import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/auth/login',
    method: 'post',
    contentType: "application/json",
    data: data
  })
}

export function logout(data) {
    return request({
      url: '/admin/auth/logout',
      method: 'post',
      contentType: "application/json",
      data: data
    })
  }