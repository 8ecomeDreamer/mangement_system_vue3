import request from '@/utils/request'

export function findAllRole() {
  return request({
    url: '/admin/system/sysRole/findAll',
    method: 'get'
  })
}

