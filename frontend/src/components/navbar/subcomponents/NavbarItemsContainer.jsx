export default function NavbarItemsContainer({
  children,
  className,
  ...props
}) {
  return (
    <div id="buttons" className={className + " w-fit"} {...props}>
      {children}
    </div>
  );
}
