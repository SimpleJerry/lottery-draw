import axios from "axios"

const URL_AWARD = '/award'

export function list(param) {
    console.log("请求参数为：", param)
    return axios({
        url: `${URL_AWARD}/`,
        method: 'post',
        data: param
    })
}