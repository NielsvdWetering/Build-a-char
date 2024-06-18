import useApi from "./useApi";
import useAccessToken from "./useAccessToken";

export default function useAuthentication() {
  const { get, post } = useApi();
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

    if (token === undefined || token === null) {
      return Promise.resolve(false);
    }

    get("auth/validate-token", { token: token })
      .then((response) => response.data)
      .catch((error) => false);

    return Promise.resolve(true);
  };

  return {
    login,
    register,
    logout,
    isLoggedIn,
  };
}
