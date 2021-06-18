import axios from "axios"

const URL_AWARD = '/award'

export function showOne(assetId) {
    return axios({
        url: `${URL_AWARD}/show-one/${assetId}`,
        method: 'get'
    })
}
export function showAll(groupId) {
    return axios({
        url: `${URL_AWARD}/show-all/${groupId}`,
        method: 'get'
    })
}