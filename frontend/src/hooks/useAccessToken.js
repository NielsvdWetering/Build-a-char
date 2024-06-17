const JWT_STORAGE_LOCATION = "JWT";

export default function useAccessToken() {
  const getToken = () => sessionStorage.getItem(JWT_STORAGE_LOCATION);
  const setToken = (token) =>
    sessionStorage.setItem(JWT_STORAGE_LOCATION, token);
  const removeToken = () => sessionStorage.removeItem(JWT_STORAGE_LOCATION);

  return {
    getToken,
    setToken,
    removeToken,
  };
}
