import axios from "axios";
import useAccessToken from "./useAccessToken";

const BASE_URL = "http://localhost:8080/api/v1/";

export default function useApi() {
  const { getToken } = useAccessToken();

  const get = (path, params) => {
    return axios
      .get(BASE_URL + path, { headers: getHeaders(), params })
      .then((response) => response.data);
  };

  const post = (path, data) => {
    return axios
      .post(BASE_URL + path, data, { headers: getHeaders() })
      .then((response) => response.data);
  };
  const patch = (path, data) => {
    return axios.patch(BASE_URL + path, data, { headers: getHeaders() });
  };

  return {
    get,
    post,
    patch,
  };

  function getHeaders() {
    const headers = {
      "Content-Type": "application/json",
    };

    const accessToken = getToken();
    if (accessToken) {
      headers["Authorization"] = `Bearer ${accessToken}`;
    }

    return headers;
  }
}
