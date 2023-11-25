import request from '@/utils/request'
export function selectDept(data) {
  return request({
    url: '/admin/system/dept/selectDept',
    method: 'post',
    data
  })
}

export function insertDept(data) {
  return request({
    url: '/admin/system/dept/insertDept',
    method: 'post',
    data
  })
}

export function updateDept(data) {
  return request({
    url: '/admin/system/dept/updateDept',
    method: 'post',
    data
  })
}

export function deleteDept(data) {
  return request({
    url: '/admin/system/dept/deleteDept',
    method: 'post',
    data
  })
}
