import request from '@/utils/request'
/**
 * @description 查询所有信息
 * @returns {*}
 * @param data
 */
export function selectMenu(data) {
  return request({
    url: '/admin/system/menu/selectMenu',
    method: 'post',
    data
  })
}


export function insertMenu(data) {
  return request({
    url: '/admin/system/menu/insertMenu',
    method: 'post',
    data
  })
}


export function updateMenu(data) {
  return request({
    url: '/admin/system/menu/updateMenu',
    method: 'post',
    data
  })
}


export function deleteMenu(data) {
  return request({
    url: '/admin/system/menu/deleteMenu',
    method: 'post',
    data
  })
}

