import { useNavigate } from "react-router-dom";
import AuthForm from "./AuthForm";
import { useAccessToken } from "../../hooks";

export default function AuthPage({
  name,
  redirectText,
  redirectURL,
  onSubmit,
}) {
  const navigate = useNavigate();
  const { setToken } = useAccessToken();

  return (
    <AuthForm
      onSubmit={onSubmit}
      submitText={name}
      redirectText={redirectText}
      onRedirect={handleRedirect}
      onCompleted={handleCompleted}
    />
  );

  function handleRedirect() {
    if (!redirectURL) {
      console.error("redirectURL not found");
      return;
    }

    navigate(redirectURL);
  }

  function handleCompleted() {
    navigate("/");
  }
}
