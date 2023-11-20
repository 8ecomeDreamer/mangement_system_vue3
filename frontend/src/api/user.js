import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/system/user/login2',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/system/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

/**
 * @description 查询所有信息
 * @returns {*}
 * @param data
 */
export function selectUserInfo(data) {
  return request({
    url: '/admin/system/user/selectUserInfo',
    method: 'post',
    data
  })
}


export function insertUserInfo(data) {
  return request({
    url: '/admin/system/user/insertUserInfo',
    method: 'post',
    data
  })
}


export function updateUserInfo(data) {
  return request({
    url: '/admin/system/user/updateUserInfo',
    method: 'post',
    data
  })
}


export function deleteUserInfo(data) {
  return request({
    url: '/admin/system/user/deleteUserInfo',
    method: 'post',
    data
  })
}

