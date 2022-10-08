/**
  * Using the addMenuProvider() API directly in your Activity
  **/
class ExampleActivity : ComponentActivity(R.layout.activity_example) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Add menu items without overriding methods in the Activity
   addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuInflater.inflate(R.menu.example_menu, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
        return true
      }
    })
  }
}

/**
  * Using the addMenuProvider() API in a Fragment
  **/
class ExampleFragment : Fragment(R.layout.fragment_example) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    // The usage of an interface lets you inject your own implementation
    val menuHost: MenuHost = requireActivity()
  
    // Add menu items without using the Fragment Menu APIs
    // Note how we can tie the MenuProvider to the viewLifecycleOwner
    // and an optional Lifecycle.State (here, RESUMED) to indicate when
    // the menu should be visible
    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuInflater.inflate(R.menu.example_menu, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
            return when (menuItem.itemId) {
                R.id.menu_clear -> {
                    // clearCompletedTasks()
                    true
                }
                R.id.menu_refresh -> {
                    // loadTasks(true)
                    true
                }
                else -> false
            }
      }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
  }
