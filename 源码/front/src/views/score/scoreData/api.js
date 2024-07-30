import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getScoreDataOne = (params) => {
    return getRequest('/scoreData/getOne', params)
}
export const getScoreDataList = (params) => {
    return getRequest('/scoreData/getByPage', params)
}
export const getScoreDataCount = (params) => {
    return getRequest('/scoreData/count', params)
}
export const addScoreData = (params) => {
    return postRequest('/scoreData/insert', params)
}
export const editScoreData = (params) => {
    return postRequest('/scoreData/update', params)
}
export const addOrEditScoreData = (params) => {
    return postRequest('/scoreData/insertOrUpdate', params)
}
export const deleteScoreData = (params) => {
    return postRequest('/scoreData/delByIds', params)
}
export const getScoreProjectList = (params) => {
    return getRequest('/scoreProject/getAll', params)
}
export const getOneScoreProjectList = (params) => {
    return getRequest('/scoreProject/getOne', params)
}
export const getUserList = (params) => {
    return getRequest('/superUser/getAll', params)
}
