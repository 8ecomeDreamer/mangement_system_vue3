import request from '@/utils/request'
export function selectRole(data) {
  return request({
    url: '/admin/system/role/selectRole',
    method: 'post',
    data
  })
}

export function insertRole(data) {
  return request({
    url: '/admin/system/role/insertRole',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: '/admin/system/role/updateRole',
    method: 'post',
    data
  })
}

export function deleteRole(data) {
  return request({
    url: '/admin/system/role/deleteRole',
    method: 'post',
    data
  })
}
