import axios from "axios"

const URL_JOB = '/job'

export function list(param) {
    console.log("请求参数为：", param)
    return axios({
        url: `${URL_JOB}/`,
        method: 'post',
        data: param
    })
}