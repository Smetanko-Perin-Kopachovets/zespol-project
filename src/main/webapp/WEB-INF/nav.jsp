<nav class="navbar is-transparent is-dark ">
    <div class="navbar-brand">
        <i class="fab fa-accusoft fa-3x"></i>
        <a class="navbar-item">

        </a>

        <div class="navbar-burger burger" data-target="navbar">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>

    <div id="navbar" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="/stores">
                Stores
            </a>

            <a class="navbar-item" href="/jobs">
                Jobs
            </a>

            <a class="navbar-item" href="/generatePDF">
                PDF generator
            </a>

            <a class="navbar-item" href="/reservation">
                Reservation
            </a>
        </div>

        <div class="navbar-end">
            <div class="navbar-item">

                <div class="navbar-item has-dropdown is-hoverable has-text-centered">
                    <a class="navbar-link">
                        ${principal.email}
                    </a>
                    <div class="navbar-dropdown is-boxed has-text-centered">
                        <a class="navbar-item  ">
                            <a href="/#" class="button ">
                                Account
                            </a>
                        </a>
                        <hr class="navbar-divider">
                        <a class="navbar-item ">
                            <a href="/logout" class="button is-danger">
                                Logout
                            </a>
                        </a>
                    </div>
                </div>

                <a class="button" style="margin-right: 20px"
                   href="https://github.com/Smetanko-Perin-Kopachovets/zespol-project">
                    <span class="icon">
                        <i class="fab fa-github"></i>
                    </span>
                    <span>GitHub</span>
                </a>

                <a class="button" href="/sendMail">
                    <span class="icon">
                        <i class="far fa-envelope"></i>
                    </span>
                    <span>SendMail</span>
                </a>


            </div>
        </div>
    </div>
</nav>