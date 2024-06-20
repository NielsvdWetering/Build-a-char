import useAuthentication from "../../../hooks/useAuthentication";
import AuthPage from "../AuthPage";

export default function LoginPage() {
  const { login } = useAuthentication();

  return (
    <AuthPage
      name="Login"
      onSubmit={login}
      redirectText="or register if you do not have an account"
      redirectURL="/register"
    />
  );
}
