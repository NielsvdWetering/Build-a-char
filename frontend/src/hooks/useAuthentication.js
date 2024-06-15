import axios from "axios";

const JWT_STORAGE_LOCATION = "JWT";

export default function useAuthentication() {
  const login = (loginData) => {
    return axios
      .post("http://localhost:8080/api/v1/auth/login", loginData)
      .then((response) => {
        sessionStorage.setItem(JWT_STORAGE_LOCATION, response.data.token);
        return response;
      });
  };

  const register = (registerData) => {
    return axios.post(
      "http://localhost:8080/api/v1/auth/register",
      registerData,
    );
  };

  const logout = () => {
    sessionStorage.removeItem(JWT_STORAGE_LOCATION);
  };

  const getAccessToken = () => {
    return sessionStorage.getItem(JWT_STORAGE_LOCATION);
  };

  const isLoggedIn = () =>
    getAccessToken() !== undefined && getAccessToken() !== null;

  return {
    login,
    register,
    logout,
    getAccessToken,
    isLoggedIn,
  };
}
