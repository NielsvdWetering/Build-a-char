package nl.itvitae.buildachar.helpers;


public record Result<TResult>(boolean succeeded, TResult body, String error) {
  public static <T> Result<T> succesResult(T body) {
    return new Result<>(true, body, null);
  }

  public static <T> Result<T> errorResult(String error) {
    return new Result<>(false, null, error);
  }
}
