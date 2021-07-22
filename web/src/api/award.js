import axios from "axios";

const URL_AWARD = "/award";

export function list(param) {
  console.log("请求参数为：", param);
  return axios({
    url: `${URL_AWARD}/`,
    method: "post",
    data: param,
  });
}

export function query(awardId) {
  console.log("awardId：", awardId);
  return axios({
    url: `${URL_AWARD}/${awardId}`,
    method: "get",
  });
}

export function reset(awardId) {
  console.log("awardId：", awardId);
  return axios({
    url: `${URL_AWARD}/${awardId}/reset`,
    method: "put",
  });
}
