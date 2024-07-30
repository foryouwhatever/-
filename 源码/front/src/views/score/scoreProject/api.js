import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getScoreProjectOne = (params) => {
    return getRequest('/scoreProject/getOne', params)
}
export const getScoreProjectList = (params) => {
    return getRequest('/scoreProject/getByPage', params)
}
export const getScoreProjectCount = (params) => {
    return getRequest('/scoreProject/count', params)
}
export const addScoreProject = (params) => {
    return postRequest('/scoreProject/insert', params)
}
export const editScoreProject = (params) => {
    return postRequest('/scoreProject/update', params)
}
export const addOrEditScoreProject = (params) => {
    return postRequest('/scoreProject/insertOrUpdate', params)
}
export const deleteScoreProject = (params) => {
    return postRequest('/scoreProject/delByIds', params)
}