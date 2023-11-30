import request from '@/utils/request'
/**
 * @description 日志管理
 */
export function selectOperLog(data) {
  return request({
    url: '/admin/system/operLog/selectOperLog',
    method: 'post',
    data
  })
}
