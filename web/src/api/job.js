import axios from "axios"

const URL_JOB = '/job'

export function listJob(groupId) {
    return axios({
        url: `${URL_JOB}/list/${groupId}`,
        method: 'get'
    })
}
export function listEmployeeId(jobId) {
    return axios({
        url: `${URL_JOB}/list/result/${jobId}`,
        method: 'get'
    })
}