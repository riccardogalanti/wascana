package com.googlecode.wascana.generator;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

public class Generator implements IApplication {

	private static final String REPO_NAME = "Wascana";

	private IMetadataRepository metaRepo;
	private IArtifactRepository artiRepo;
	
	private String gplLicenseText;
	private String lgplLicenseText;
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		
		String[] args = (String[])context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
		if (args.length < 1) {
			System.err.println("usage: <repoDir>");
			return EXIT_OK;
		}
		
		File repoDir = new File(args[0]);
		createRepos(repoDir);
		loadLicenses();
		
		return EXIT_OK;
	}

	private void createRepos(File repoDir) throws ProvisionException {
		repoDir.mkdirs();
		
		new File(repoDir, "artifacts.jar").delete();
		new File(repoDir, "content.jar").delete();
		
		URI repoLocation = repoDir.toURI();

		IMetadataRepositoryManager metaRepoMgr = Activator.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager artiRepoMgr = Activator.getService(IArtifactRepositoryManager.class);
		
		metaRepo = metaRepoMgr.createRepository(repoLocation, REPO_NAME, IMetadataRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);
		metaRepo.setProperty(IRepository.PROP_COMPRESSED, Boolean.TRUE.toString());
		
		artiRepo = artiRepoMgr.createRepository(repoLocation, REPO_NAME, IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);
		artiRepo.setProperty(IRepository.PROP_COMPRESSED, Boolean.TRUE.toString());
	}
	
	private void loadLicenses() throws IOException {
		gplLicenseText = Activator.getFileContents(new Path("licenses/gpl-2.0.txt"));
		lgplLicenseText = Activator.getFileContents(new Path("licenses/lgpl-2.1.txt"));
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
