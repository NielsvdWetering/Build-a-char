import useApi from "./useApi";
import useAccessToken from "./useAccessToken";

export default function useAuthentication() {
  const { post } = useApi();
  const { getToken, setToken, removeToken } = useAccessToken();

  const login = (loginData) => {
    return post("auth/login", loginData).then((response) => {
      setToken(response.token);
      return response;
    });
  };

  const register = (registerData) => {
    return post("auth/register", registerData);
  };

  const logout = () => {
    removeToken();
  };

  const isLoggedIn = () => {
    const token = getToken();
    return token !== undefined && token !== null;
  };

  return {
    login,
    register,
    logout,
    isLoggedIn,
  };
}
